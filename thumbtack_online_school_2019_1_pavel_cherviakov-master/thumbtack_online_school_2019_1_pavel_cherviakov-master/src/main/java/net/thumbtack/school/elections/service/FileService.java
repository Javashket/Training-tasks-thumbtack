package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.AllDataDto;
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
        File file = new File(savedDataFileName);
        byte[] array = Files.readAllBytes(file.toPath());
        AllDataDto allDataDto = new Gson().fromJson(new String(array), AllDataDto.class);
        mayorCandidateDao.insert(),
                offerDao.getAll(),
                voterDao.getAll(),
                ratingDao.getAll()
    }

    public void writeToFile(String saveDataFileName) throws IOException {
        AllDataDto allDataDto = new AllDataDto(
                mayorCandidateDao.getAll(),
                offerDao.getAll(),
                voterDao.getAll(),
                ratingDao.getAll()
        );
        String saveData = new Gson().toJson(allDataDto);
        File file = new File(saveDataFileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(saveData.getBytes());
        fileOutputStream.close();
    }
}
