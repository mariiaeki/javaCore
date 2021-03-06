package homework_16_2401_java8.transportation.service;

import homework_16_2401_java8.transportation.domain.Transportation;
import homework_16_2401_java8.transportation.repo.TrsRepo;

import java.io.Serializable;
import java.util.List;

public class TrsServiceImpl implements TrsService, Serializable {
    private final TrsRepo trsRepo;

    public TrsServiceImpl(TrsRepo trsRepo) {
        this.trsRepo = trsRepo;
    }

    @Override
    public void add(Transportation trs) {
        if (trs != null){
            trsRepo.add(trs);
        }

    }

    @Override
    public Transportation getById(Long id) {
        if(id != null){
            return trsRepo.getById(id);
        }
        return null;
    }

    @Override
    public List<Transportation> getAll() {
        return trsRepo.getAll();
    }

    @Override
    public void printAll() {
        List<Transportation> allTrs = trsRepo.getAll();

        for (Transportation transportation: allTrs){
            System.out.println(transportation);
        }
    }

    @Override
    public void update(Transportation trs) {
        if (trs != null){
            trsRepo.update(trs);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (id != null){
            return trsRepo.deleteById(id);
        }
        return false;
    }

    @Override
    public String toString() {
        return "TrsServiceImpl{" +
                "trsRepo=" + trsRepo +
                '}';
    }
}
