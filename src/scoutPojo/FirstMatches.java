package scoutPojo;

public class FirstMatches {
    private Matches[] Matches;

    public Matches[] getMatches() {
        return Matches;
    }

    public void setMatches(Matches[] Matches) {
        this.Matches = Matches;
    }

    @Override
    public String toString() {
        return "ClassPojo [Matches = " + Matches + "]";
    }

    public class Matches {
        private String scoreRedAuto;

        private String actualStartTime;

        private Teams[] teams;

        private String scoreRedFinal;

        private String postResultTime;

        private String description;

        private String scoreBlueAuto;

        private String scoreBlueFinal;

        private String matchNumber;

        private String scoreRedFoul;

        private String scoreBlueFoul;

        private String tournamentLevel;

        public String getScoreRedAuto() {
            return scoreRedAuto;
        }

        public void setScoreRedAuto(String scoreRedAuto) {
            this.scoreRedAuto = scoreRedAuto;
        }

        public String getActualStartTime() {
            return actualStartTime;
        }

        public void setActualStartTime(String actualStartTime) {
            this.actualStartTime = actualStartTime;
        }

        public Teams[] getTeams() {
            return teams;
        }

        public void setTeams(Teams[] teams) {
            this.teams = teams;
        }

        public String getScoreRedFinal() {
            return scoreRedFinal;
        }

        public void setScoreRedFinal(String scoreRedFinal) {
            this.scoreRedFinal = scoreRedFinal;
        }

        public String getPostResultTime() {
            return postResultTime;
        }

        public void setPostResultTime(String postResultTime) {
            this.postResultTime = postResultTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getScoreBlueAuto() {
            return scoreBlueAuto;
        }

        public void setScoreBlueAuto(String scoreBlueAuto) {
            this.scoreBlueAuto = scoreBlueAuto;
        }

        public String getScoreBlueFinal() {
            return scoreBlueFinal;
        }

        public void setScoreBlueFinal(String scoreBlueFinal) {
            this.scoreBlueFinal = scoreBlueFinal;
        }

        public String getMatchNumber() {
            return matchNumber;
        }

        public void setMatchNumber(String matchNumber) {
            this.matchNumber = matchNumber;
        }

        public String getScoreRedFoul() {
            return scoreRedFoul;
        }

        public void setScoreRedFoul(String scoreRedFoul) {
            this.scoreRedFoul = scoreRedFoul;
        }

        public String getScoreBlueFoul() {
            return scoreBlueFoul;
        }

        public void setScoreBlueFoul(String scoreBlueFoul) {
            this.scoreBlueFoul = scoreBlueFoul;
        }

        public String getTournamentLevel() {
            return tournamentLevel;
        }

        public void setTournamentLevel(String tournamentLevel) {
            this.tournamentLevel = tournamentLevel;
        }

        @Override
        public String toString() {
            return "ClassPojo [scoreRedAuto = " + scoreRedAuto + ", actualStartTime = " + actualStartTime + ", teams = "
                    + teams + ", scoreRedFinal = " + scoreRedFinal + ", postResultTime = " + postResultTime
                    + ", description = " + description + ", scoreBlueAuto = " + scoreBlueAuto + ", scoreBlueFinal = "
                    + scoreBlueFinal + ", matchNumber = " + matchNumber + ", scoreRedFoul = " + scoreRedFoul
                    + ", scoreBlueFoul = " + scoreBlueFoul + ", tournamentLevel = " + tournamentLevel + "]";
        }
    }

    public class Teams {
        private String teamNumber;

        private String station;

        private String dq;

        public String getTeamNumber() {
            return teamNumber;
        }

        public void setTeamNumber(String teamNumber) {
            this.teamNumber = teamNumber;
        }

        public String getStation() {
            return station;
        }

        public void setStation(String station) {
            this.station = station;
        }

        public String getDq() {
            return dq;
        }

        public void setDq(String dq) {
            this.dq = dq;
        }

        @Override
        public String toString() {
            return "ClassPojo [teamNumber = " + teamNumber + ", station = " + station + ", dq = " + dq + "]";
        }
    }
}