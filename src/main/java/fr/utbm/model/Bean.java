package fr.utbm.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.util.Objects;

public class Bean implements Serializable {

    private int id;

    private String test;

    private Boolean actif;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bean bean = (Bean) o;
        return id == bean.id &&
                Objects.equals(test, bean.test) &&
                Objects.equals(actif, bean.actif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, test, actif);
    }

    @Override
    public String toString() {
        return "Bean{" +
                "id=" + id +
                ", test='" + test + '\'' +
                ", actif=" + actif +
                '}';
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getActif() {
        return actif;
    }

    public String getTest() {
        return test;
    }

    public int getId() {
        return id;
    }
}
