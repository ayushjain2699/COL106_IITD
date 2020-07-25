package col106.assignment3.Election;
class Candidate{
    public String name;
    public String candID;
    public String state;
    public String district;
    public String constituency;
    public String party;
    public String votes;

    Candidate(String _name, String _candID, String _state, String _district, String _constituency, String _party, String _votes)
    {
        name = _name;
        candID = _candID;
        state = _state;
        district = _district;
        constituency = _constituency;
        party = _party;
        votes = _votes;
    }
}