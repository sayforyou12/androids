package com.cafe.common.Model;

public class ModelCafeLike {
    
    private Integer cafeno;
    private Integer userno;
    
    
    public Integer getCafeno() {
        return cafeno;
    }
    public void setCafeno(Integer cafeno) {
        this.cafeno = cafeno;
    }
    public Integer getUserno() {
        return userno;
    }
    public void setUserno(Integer userno) {
        this.userno = userno;
    }
    public ModelCafeLike(Integer cafeno, Integer userno) {
        super();
        this.cafeno = cafeno;
        this.userno = userno;
    }
    public ModelCafeLike() {
        super();
    }
    @Override
    public String toString() {
        return "ModelCafeLike [cafeno=" + cafeno + ", userno=" + userno + "]";
    }
    
    
}
