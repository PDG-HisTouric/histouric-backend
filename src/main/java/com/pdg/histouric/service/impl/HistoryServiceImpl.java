package com.pdg.histouric.service.impl;

import com.pdg.histouric.constant.UserErrorCode;
import com.pdg.histouric.error.exception.UserError;
import com.pdg.histouric.error.exception.UserException;
import com.pdg.histouric.model.History;
import com.pdg.histouric.model.HistoryImage;
import com.pdg.histouric.model.Text;
import com.pdg.histouric.model.Video;
import com.pdg.histouric.repository.*;
import com.pdg.histouric.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final VideoRepository videoRepository;
    private final TextRepository textRepository;
    private final HistoryImageRepository historyImageRepository;
    private final UserRepository userRepository;

    @Override
    public History createHistory(History history) {
        List<Text> texts = history.getTexts();
        List<Video> videos = history.getVideos();
        List<HistoryImage> images = history.getImages();
        history.setTexts(null);
        history.setVideos(null);
        history.setImages(null);
        history.setOwner(userRepository.findById(history.getOwner().getId()).orElseThrow(
                () -> new UserException(
                        HttpStatus.NOT_FOUND,
                        new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())
                )
        ));
        History savedHistory = historyRepository.save(history);
        if (videos != null) history.setVideos(videoRepository.saveAll(videos.stream().peek(video -> video.setHistory(savedHistory)).toList()));
        if (images != null) history.setImages(historyImageRepository.saveAll(images.stream().peek(historyImage -> historyImage.setHistory(savedHistory)).toList()));
        history.setTexts(textRepository.saveAll(texts.stream().peek(text -> text.setHistory(savedHistory)).toList()));
        return history;
    }
}