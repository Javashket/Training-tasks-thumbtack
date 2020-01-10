package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.AllDataDto;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.mybatis.dao.MayorCandidateDao;
import net.thumbtack.school.elections.mybatis.dao.OfferDao;
import net.thumbtack.school.elections.mybatis.dao.RatingDao;
import net.thumbtack.school.elections.mybatis.dao.VoterDao;
import net.thumbtack.school.elections.mybatis.daoimpl.MayorCandidateDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.OfferDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.RatingDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.VoterDaoImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    private VoterDao voterDao;
    private OfferDao offerDao;
    private RatingDao ratingDao;
    private MayorCandidateDao mayorCandidateDao;

    public FileService() {
        this.voterDao = new VoterDaoImpl();
        this.offerDao = new OfferDaoImpl();
        this.ratingDao = new RatingDaoImpl();
        this.mayorCandidateDao = new MayorCandidateDaoImpl();
    }

    public void readFromFile(String savedDataFileName) throws IOException {
        // добавить голоса в сохранения
        File file = new File(savedDataFileName);
        System.out.println(file.getAbsolutePath());
        Path path = Paths.get(savedDataFileName);
        byte[] array = Files.readAllBytes(path);
        AllDataDto allDataDto = new Gson().fromJson(new String(array), AllDataDto.class);
        if(!allDataDto.getVoters().isEmpty()) {
            voterDao.batchInsert(allDataDto.getVoters());
        }
        if(!allDataDto.getMayorCandidates().isEmpty()) {
            mayorCandidateDao.batchInsert(allDataDto.getMayorCandidates());
        }
        if(!allDataDto.getOffers().isEmpty()) {
            offerDao.batchInsert(allDataDto.getOffers());
            for(Offer offer : allDataDto.getOffers()) {
                if(!offer.getRatings().isEmpty()) {
                    for (Rating rating : offer.getRatings()) {
                        ratingDao.insert(rating, offer);
                    }
                }
            }
        }
    }

    public void writeToFile(String saveDataFileName) throws IOException {
        AllDataDto allDataDto = new AllDataDto(
                mayorCandidateDao.getAll(),
                offerDao.getAll(),
                voterDao.getAll()
        );
        String saveData = new Gson().toJson(allDataDto);
        File file = new File(saveDataFileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(saveData.getBytes());
        fileOutputStream.close();
    }
}
