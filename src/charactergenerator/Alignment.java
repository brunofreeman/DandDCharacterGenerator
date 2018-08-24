package charactergenerator;

public enum Alignment {
    LG, NG, CG, LN, N, CN, LE, NE, CE;

    @Override
    public String toString() {
        switch (this) {
            case LG: return "Lawful Good";
            case NG: return "Neutral Good";
            case CG: return "Chaotic Good";
            case LN: return "Lawful Neutral";
            case N:  return "Neutral";
            case CN: return "Chaotic Neutral";
            case LE: return "Lawful Evil";
            case NE: return "Neutral Evil";
            case CE: return "Chaotic Evil";
            default: return "Alignment";
        }
    }
    
    public SubAlignment getSA1() {
        switch (this) {
            case LG:
            case LN:
            case LE: return SubAlignment.L;
            case NG:
            case N:
            case NE: return SubAlignment.N;
            case CG:
            case CN:
            case CE: return SubAlignment.C;
            default: return null;
        }
    }
    
    public SubAlignment getSA2() {
        switch (this) {
            case LG:
            case NG:
            case CG: return SubAlignment.G;
            case LN:
            case N:
            case CN: return SubAlignment.N;
            case LE:
            case NE: 
            case CE: return SubAlignment.E;
            default: return null;
        }
    }
}
