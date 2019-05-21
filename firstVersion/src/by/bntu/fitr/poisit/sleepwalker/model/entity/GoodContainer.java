package by.bntu.fitr.poisit.sleepwalker.model.entity;

import by.bntu.fitr.poisit.sleepwalker.util.JsonWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoodContainer {


    private List<Suit> suitList;
    private List<Footwear> footwearList;
    private List<ProtectionMean> protectionMeanList;

    public GoodContainer() {
        suitList = new ArrayList<>();
        footwearList = new ArrayList<>();
        protectionMeanList = new ArrayList<>();
    }

    public List<Suit> getSuitList() {
        return suitList;
    }

    public void setSuitList(List<Suit> suitList) {
        this.suitList = suitList;
    }

    public List<Footwear> getFootwearList() {
        return footwearList;
    }

    public List<ProtectionMean> getProtectionMeanList() {
        return protectionMeanList;
    }

    public void setProtectionMeanList(List<ProtectionMean> protectionMeanList) {
        this.protectionMeanList = protectionMeanList;
    }

    public void setFootwearList(List<Footwear> footwearList) {
        this.footwearList = footwearList;
    }

    public List<Good> getAll(){
        List<Good> all = new ArrayList<>(suitList);
        all.addAll(footwearList);
        all.addAll(protectionMeanList);
        return all;
    }

    public void saveInJson() throws IOException {
        JsonWorker.write(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodContainer that = (GoodContainer) o;
        return Objects.equals(suitList, that.suitList) &&
                Objects.equals(footwearList, that.footwearList) &&
                Objects.equals(protectionMeanList, that.protectionMeanList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suitList, footwearList, protectionMeanList);
    }

    @Override
    public String toString() {
        return "GoodContainer{" +
                "suitList=" + suitList +
                ", footwearList=" + footwearList +
                ", protectionMeanList=" + protectionMeanList +
                '}';
    }
}
