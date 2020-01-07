package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.MayorCandidate;

import java.util.List;

public class AllCandidatesDtoResponse {

    private List<MayorCandidate> mayorCandidates;

    public AllCandidatesDtoResponse() {
    }

    public AllCandidatesDtoResponse(List<MayorCandidate> mayorCandidates) {
        this.mayorCandidates = mayorCandidates;
    }

    public List<MayorCandidate> getMayorCandidates() {
        return mayorCandidates;
    }

    public void setMayorCandidates(List<MayorCandidate> mayorCandidates) {
        this.mayorCandidates = mayorCandidates;
    }
}
