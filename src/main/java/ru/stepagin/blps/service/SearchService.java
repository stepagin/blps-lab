package ru.stepagin.blps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.mapper.IssueMapper;
import ru.stepagin.blps.repository.IssueRepository;
import ru.stepagin.blps.repository.IssueTagRepository;
import ru.stepagin.blps.repository.IssueWithTagsInterface;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final IssueRepository issueRepository;
    private final IssueTagRepository issueTagRepository;

    public List<IssueDto> getAll(PageRequest pageRequest) {
        return issueRepository.findAllWithTags(pageRequest).stream()
                .map(IssueMapper::toDtoFromInterface).toList();
    }

    public List<IssueDto> getAll(List<String> tagNames, PageRequest pageRequest) {
        if (tagNames.isEmpty()) {
            throw new IllegalArgumentException("tag names list is empty");
        }
        return issueTagRepository.findAllByTags(tagNames, pageRequest).stream().map(IssueMapper::toDtoFromInterface).toList();
    }

    public List<IssueDto> getAll(String title, List<String> tags, PageRequest pageRequest) {
        if ((tags == null || tags.isEmpty()) && title.isEmpty()) {
            return this.getAll(pageRequest);
        }
        if (title.isEmpty()) {
            return this.getAll(tags, pageRequest);
        }
        if (tags == null || tags.isEmpty()) {
            return IssueMapper.toDtoFromInterface(issueRepository.findByTitle(title, pageRequest));
        }
        List<IssueWithTagsInterface> issueTagsEntities = issueTagRepository.findByTagsAndTitle(tags, title, pageRequest);
        return IssueMapper.toDtoFromInterface(issueTagsEntities);

    }
}

