package com.huh.BaekJoonSupporter.boundedContext.line;


import com.huh.BaekJoonSupporter.boundedContext.team.Team;
import com.huh.BaekJoonSupporter.customException.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LineService {

    private final LineRepository lineRepository;

    public Line create(String token, Team team) {
        Line line = Line.builder()
                .token(token)
                .team(team)
                .build();
        return lineRepository.save(line);
    }

    public Line getLine(Long id) {
        return lineRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Line not found."));
    }

    public Line getLine(String token) {
        return lineRepository.findByToken(token)
                .orElseThrow(() -> new DataNotFoundException("Line not found."));
    }

    public void delete(Line line) {
        lineRepository.delete(line);
    }

    public void modify(Line line, String m_token, Team m_team) {
        Line m_line = line.toBuilder()
                .token(m_token)
                .team(m_team)
                .build();
        this.lineRepository.save(m_line);
    }
}