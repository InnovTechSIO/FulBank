package fr.innovtech.fulbank.entities;

public class Type {

    private int _id;
    private String _wording;

    public Type(int id, String wording){
        this._id = id;
        this._wording=wording;
    }

    public int getId() { return this._id; }

    public String getWording() { return this._wording; }

    public void setId(int id) { this._id = id; }

    public void setWording (String wording) {this._wording = wording;}
    @Override
    public String toString() {
        return "Type{" +
                "_idType=" + _id +
                ", _wording='" + _wording +
                '}';
    }
}

}
