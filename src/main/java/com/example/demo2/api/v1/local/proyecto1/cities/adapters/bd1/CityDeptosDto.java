
package com.example.demo2.api.v1.local.proyecto1.cities.adapters.bd1;


public class CityDeptosDto {
    
    private Integer code_mun;
    private String mun;
    private Integer code_dep;
    private String dep;

    public CityDeptosDto() {
    }
    
    public CityDeptosDto( Object[] cols ) {
        this.code_mun = ( Integer ) cols[0];
        this.mun = ( cols[1] != null ) ? ( String ) cols[1]  : "";
        this.code_dep = ( Integer ) cols[2];
        this.dep = ( cols[3] != null ) ? ( String ) cols[3]  : "";
    }

    public CityDeptosDto(Integer code_mun, String mun, Integer code_dep, String dep) {
        this.code_mun = code_mun;
        this.mun = mun;
        this.code_dep = code_dep;
        this.dep = dep;
    }

    public Integer getCode_mun() {
        return code_mun;
    }

    public void setCode_mun(Integer code_mun) {
        this.code_mun = code_mun;
    }

    public String getMun() {
        return mun;
    }

    public void setMun(String mun) {
        this.mun = mun;
    }

    public Integer getCode_dep() {
        return code_dep;
    }

    public void setCode_dep(Integer code_dep) {
        this.code_dep = code_dep;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }
    
    
    
}
