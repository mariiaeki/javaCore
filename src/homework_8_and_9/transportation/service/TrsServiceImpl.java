package homework_8_and_9.transportation.service;

import homework_8_and_9.transportation.domain.Transportation;
import homework_8_and_9.transportation.repo.TrsRepo;

import java.util.List;

public class TrsServiceImpl implements TrsService {
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
        try{
            return trsRepo.deleteById(id);
        }catch (NullPointerException e){
            return false;
        }
    }
}
