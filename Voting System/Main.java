import java.util.*;

class Voter {
    private String id;
    private boolean hasVoted;

    public Voter(String id) {
        this.id = id;
        this.hasVoted = false;
    }

    public String getId() {
        return id;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void vote() {
        this.hasVoted = true;
    }
}

class Candidate {
    private String name;
    private int votes;

    public Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public void addVote() {
        this.votes++;
    }
}

class VotingSystem {
    private Map<String, Voter> voters;
    private List<Candidate> candidates;

    public VotingSystem() {
        this.voters = new HashMap<>();
        this.candidates = new ArrayList<>();
    }

    public void addVoter(String voterId) {
        voters.put(voterId, new Voter(voterId));
    }

    public void addCandidate(String candidateName) {
        candidates.add(new Candidate(candidateName));
    }

    public boolean vote(String voterId, String candidateName) {
        Voter voter = voters.get(voterId);
        if (voter == null) {
            System.out.println("Voter not found.");
            return false;
        }
        if (voter.hasVoted()) {
            System.out.println("Voter has already voted.");
            return false;
        }
        for (Candidate candidate : candidates) {
            if (candidate.getName().equals(candidateName)) {
                candidate.addVote();
                voter.vote();
                return true;
            }
        }
        System.out.println("Candidate not found.");
        return false;
    }

    public void displayResults() {
        System.out.println("Election Results:");
        for (Candidate candidate : candidates) {
            System.out.println(candidate.getName() + ": " + candidate.getVotes() + " votes");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        VotingSystem system = new VotingSystem();

        // Add voters
        system.addVoter("voter1");
        system.addVoter("voter2");

        // Add candidates
        system.addCandidate("NDA");
        system.addCandidate("INDIA");

        // Cast votes
        system.vote("voter1", "NDA");
        system.vote("voter2", "INDIA");
        system.vote("voter1", "INDIA"); // This should fail as voter1 has already voted

        // Display results
        system.displayResults();
    }
}