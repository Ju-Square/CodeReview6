package jdbc.codereview6;

public class Classes {
    private int classID;
    private String className;
    public Classes(int classID, String className){
        this.classID = classID;
        this.className = className;
    }
    @Override
    public String toString() {
        return
                className;

    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
