package com.pdg.histouric.service.impl;

import com.pdg.histouric.constant.HistoryErrorCode;
import com.pdg.histouric.constant.UserErrorCode;
import com.pdg.histouric.error.exception.HistoryError;
import com.pdg.histouric.error.exception.HistoryException;
import com.pdg.histouric.error.exception.UserError;
import com.pdg.histouric.error.exception.UserException;
import com.pdg.histouric.model.*;
import com.pdg.histouric.repository.*;
import com.pdg.histouric.service.HistoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final VideoRepository videoRepository;
    private final TextRepository textRepository;
    private final HistoryImageRepository historyImageRepository;
    private final UserRepository userRepository;
    private final AudioRepository audioRepository;

    @Override
    @Transactional
    public History createHistory(History history) {
        return saveHistory(history);
    }

    private void setOwnerToHistory (History history) {
        history.setOwner(userRepository.findById(history.getOwner().getId()).orElseThrow(
                () -> new UserException(
                        HttpStatus.NOT_FOUND,
                        new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())
                )
        ));
    }

    @Override
    @Transactional
    public void deleteHistory(UUID historyId) {
        History history = getHistoryById(historyId);
        deleteVideosImagesAndTexts(history);
        historyRepository.delete(history);
    }

    @Override
    public History getHistoryById(UUID historyId) {
        return historyRepository.findById(historyId).orElseThrow(
                () -> new HistoryException(
                        HttpStatus.NOT_FOUND,
                        new HistoryError(HistoryErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())
                )
        );
    }

    private void deleteVideosImagesAndTexts(History history) {
        videoRepository.deleteAll(history.getVideos());
        historyImageRepository.deleteAll(history.getImages());
        textRepository.deleteAll(history.getTexts());
    }

    @Override
    public History updateHistory(History history) {
        deleteVideosImagesAndTexts(getHistoryById(history.getId()));
        return saveHistory(history);
    }

    @Override
    public List<History> getHistoriesByTitle(String historyTitle) {
        return historyRepository.findAllByTitleContainsIgnoreCase(historyTitle);
    }

    @Override
    public List<History> getAllHistories() {
        return historyRepository.findAll();
    }

    private History saveHistory(History history) {
        List<Text> texts = history.getTexts();
        List<Video> videos = history.getVideos();
        List<HistoryImage> images = history.getImages();
        history.setTexts(null);
        history.setVideos(null);
        history.setImages(null);
        setOwnerToHistory(history);
        audioRepository.save(history.getAudio());
        History savedHistory = historyRepository.save(history);
        history.getAudio().setHistory(savedHistory);
        if (videos != null) history.setVideos(videoRepository.saveAll(videos.stream().peek(video -> video.setHistory(savedHistory)).toList()));
        if (images != null) history.setImages(historyImageRepository.saveAll(images.stream().peek(historyImage -> historyImage.setHistory(savedHistory)).toList()));
        history.setTexts(textRepository.saveAll(texts.stream().peek(text -> text.setHistory(savedHistory)).toList()));
        return history;
    }
}