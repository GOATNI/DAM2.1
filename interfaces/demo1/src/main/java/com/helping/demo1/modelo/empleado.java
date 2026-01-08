package com.helping.demo1.modelo;

import java.util.Date;

public class empleado {
    int idempleado;
    String nombreEntero;
    String gmail;
    int phone;
    Date dateofbirth;
    String gender;
    boolean knows_java;
    boolean knows_python;
    boolean knows_sql;
    boolean is_full_time;
    String company_name;

    public empleado() {
    }

    public empleado(int idempleado, String nombreEntero, String gmail, int phone, Date dateofbirth, String gender, boolean knows_java, boolean knows_python, boolean knows_sql, boolean is_full_time, String company_name) {
        this.idempleado = idempleado;
        this.nombreEntero = nombreEntero;
        this.gmail = gmail;
        this.phone = phone;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.knows_java = knows_java;
        this.knows_python = knows_python;
        this.knows_sql = knows_sql;
        this.is_full_time = is_full_time;
        this.company_name = company_name;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public String getNombreEntero() {
        return nombreEntero;
    }

    public void setNombreEntero(String nombreEntero) {
        this.nombreEntero = nombreEntero;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isKnows_java() {
        return knows_java;
    }

    public void setKnows_java(boolean knows_java) {
        this.knows_java = knows_java;
    }

    public boolean isKnows_python() {
        return knows_python;
    }

    public void setKnows_python(boolean knows_python) {
        this.knows_python = knows_python;
    }

    public boolean isKnows_sql() {
        return knows_sql;
    }

    public void setKnows_sql(boolean knows_sql) {
        this.knows_sql = knows_sql;
    }

    public boolean isIs_full_time() {
        return is_full_time;
    }

    public void setIs_full_time(boolean is_full_time) {
        this.is_full_time = is_full_time;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    @Override
    public String toString() {
        return "empleado{" +
                "idempleado=" + idempleado +
                ", nombreEntero='" + nombreEntero + '\'' +
                ", gmail='" + gmail + '\'' +
                ", phone=" + phone +
                ", dateofbirth=" + dateofbirth +
                ", gender='" + gender + '\'' +
                ", knows_java=" + knows_java +
                ", knows_python=" + knows_python +
                ", knows_sql=" + knows_sql +
                ", is_full_time=" + is_full_time +
                ", company_name='" + company_name + '\'' +
                '}';
    }
}
