package net.thumbtack.school.elections.server;

import net.thumbtack.school.elections.mybatis.dao.CommonDao;
import net.thumbtack.school.elections.mybatis.daoimpl.CommonDaoImpl;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.service.*;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Server {

    private static ElectionService electionService;
    private static VoterService voterService;
    private static OfferService offerService;
    private static MayorCandidateService mayorCandidateService;
    private static FileService fileService;
    private static CommonDao commonDao;
    private static boolean turnOn;
    private static boolean startVoting;
    private static String savedDataFileName;
    private static String saveDataFileName;
    private static boolean setUpIsDone = false;

    private Server(){

    }

    public static void main(String[] args) throws IOException {
        if (!setUpIsDone) {
            boolean initSqlSessionFactory = MyBatisUtils.initSqlSessionFactory();
            if (!initSqlSessionFactory) {
                throw new RuntimeException("Can't create connection, stop");
            }
            setUpIsDone = true;
        }
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        options.addOption("l", true, "file name for read");
        options.addOption("s", true, "file name for write");
        options.addOption("e", "exit", false, "stop server");
        try {
            cmd = parser.parse( options, args);
        } catch (ParseException pe) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "server", options );
        }
        if (cmd != null && cmd.hasOption("l") && !turnOn) {
            savedDataFileName = cmd.getOptionValue("l");
        }
        if (cmd != null && cmd.hasOption("s") && !turnOn) {
            saveDataFileName = cmd.getOptionValue("s");
        }
        if(!turnOn) {
            if(savedDataFileName != null) {
                Server.startServer(savedDataFileName);
            } else {
                Server.startServer(null);
            }
        }
        if (cmd != null && cmd.hasOption("e") && turnOn) {
            if(savedDataFileName != null) {
                Server.stopServer(saveDataFileName);
            } else {
                Server.stopServer(null);
            }
            savedDataFileName = null;
            saveDataFileName = null;
        }
    }

    public static void startServer(String savedDataFileName) throws IOException {
        if(!turnOn) {
            electionService = new ElectionService();
            voterService = new VoterService();
            offerService = new OfferService();
            mayorCandidateService = new MayorCandidateService();
            fileService = new FileService();
            commonDao = new CommonDaoImpl();
            commonDao.clear();
            if(savedDataFileName != null) {
                fileService.readFromFile(savedDataFileName);
            }
            turnOn = true;
            startVoting = false;
        }
    }

    public static void stopServer(String saveDataFileName) throws IOException {
        if(turnOn) {
            if(saveDataFileName != null) {
                fileService.writeToFile(saveDataFileName);
            } else {
                voterService.deleteAllVoters();
            }
            electionService = null;
            voterService = null;
            offerService = null;
            mayorCandidateService = null;
            fileService = null;
            commonDao.clear();
            commonDao = null;
            turnOn = false;
            startVoting = false;
        }
    }

    public static boolean isStartingServer() {
        return turnOn;
    }

    public static void startVoting() {
         startVoting = true;
    }

    public static boolean isStartingVoting() {
        return startVoting;
    }

    public static String registerVoter(String jsonRequest) {
        return voterService.registerVoter(jsonRequest);
    }

    public static String logout(String jsonRequest) {
        return voterService.logout(jsonRequest);
    }

    public static String login(String jsonRequest) {
        return voterService.login(jsonRequest);
    }

    public static String addOffer(String jsonRequest) {
        return offerService.addOffer(jsonRequest);
    }

    public static String rateOffer(String jsonRequest) {
        return offerService.rateOffer(jsonRequest);
    }

    // автор не может
    public static String deleteRatingFromOffer(String requestJson) {
        return offerService.deleteRatingFromOffer(requestJson);
    }

    public static String putOnMayor(String jsonRequest) {
        return mayorCandidateService.putOnMayor(jsonRequest);
    }

    public static String withdrawCandidateWithMayor(String jsonRequest) {
        return mayorCandidateService.withdrawСandidacyWithMayor(jsonRequest);
    }

    public static String consentOnPositionOnMayor(String jsonRequest) {
        return mayorCandidateService.consentOnPositionOnMayor(jsonRequest);
    }

    public static String voteForCandidate(String jsonRequest) {
        return electionService.voteForCandidate(jsonRequest);
    }

    public static String voteAgainstAllCandidates(String jsonRequest) {
        return electionService.voteAgainstAllCandidates(jsonRequest);
    }

    public static String includeOfferInYourProgram(String jsonRequest) {
        return mayorCandidateService.includeOfferInYourProgram(jsonRequest);
    }

    // не могут удалить свои предложения
    public static String deleteOfferFromYourProgram(String jsonRequest) {
        return mayorCandidateService.deleteOfferFromYourProgram(jsonRequest);
    }

    // String jsonRequest содержит токен подтверждающий регистрацию
    public static String getAllVoters(String jsonRequest) {
        return voterService.getAllVoters(jsonRequest);
    }

    // String jsonRequest содержит токен подтверждающий регистрацию
    public static String getAllCandidates(String jsonRequest) {
        return mayorCandidateService.getAllCandidates(jsonRequest);
    }

    // String jsonRequest содержит токен подтверждающий регистрацию
    public static String  getAllOffers(String jsonRequest) {
        return offerService.getAllOffers(jsonRequest);
    }

    public static String  getOffersSeveralCandidates(String jsonRequest) {
        return offerService.getAllOffers(jsonRequest);
    }

    // передача токена передача файла места записи и возврат результата
    public static String summarize(String jsonRequest) throws IOException {
        return electionService.summarize(jsonRequest);
    }
}
