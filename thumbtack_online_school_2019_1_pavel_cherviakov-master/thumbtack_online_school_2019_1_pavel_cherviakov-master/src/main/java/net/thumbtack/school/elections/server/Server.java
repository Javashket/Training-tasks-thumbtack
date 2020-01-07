package net.thumbtack.school.elections.server;

import net.thumbtack.school.elections.mybatis.dao.CommonDao;
import net.thumbtack.school.elections.mybatis.daoimpl.CommonDaoImpl;
import net.thumbtack.school.elections.service.*;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Server {

    private static ElectionService electionService;
    private static VoterService voterService;
    private static FileService fileService;
    private static CommonDao commonDao;
    private static boolean turnOn;
    private static boolean startVoting;
    private static String savedDataFileName;
    private static String saveDataFileName;

    private Server(){

    }

    public static void main(String[] args) throws IOException {
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
            Server.startServer(savedDataFileName);
        }
        if (cmd != null && cmd.hasOption("e") && turnOn) {
            Server.stopServer(saveDataFileName);
            savedDataFileName = null;
            saveDataFileName = null;
        }
    }

    public static void startServer(String savedDataFileName) throws IOException {
        if(!turnOn) {
            electionService = new ElectionService();
            voterService = new VoterService();
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
            fileService = null;
            commonDao.clear();
            commonDao = null;
            turnOn = false;
            startVoting = false;
        }
    }

    public static boolean startVoting() {
        return turnOn ? startVoting = true : startVoting;
    }

    public static boolean isStartingVoting() {
        return startVoting;
    }

    public static boolean isStartingServer() {
        return turnOn;
    }

    public static String logout(String jsonRequest) {
        return voterService.logout(jsonRequest);
    }

    public static String login(String jsonRequest) {
        return voterService.login(jsonRequest);
    }

    public static String registerVoter(String jsonRequest) {
        return voterService.registerVoter(jsonRequest);
    }

    public static String putOnMayor(String jsonRequest) {
        return electionService.putOnMayor(jsonRequest);
    }

    public static String agreeToPositionOnMayor(String jsonRequest) {
        return electionService.agreeToPositionOnMayor(jsonRequest);
    }

    public static String addOffer(String jsonRequest) {
        return electionService.addOffer(jsonRequest);
    }

    public static String rateOffer(String jsonRequest) {
        return electionService.rateOffer(jsonRequest);
    }

    public static String vote(String jsonRequest) {
        return electionService.vote(jsonRequest);
    }

    public static String addCandidate(String jsonRequest) {
        return electionService.addCandidate(jsonRequest);
    }

    public static String getAllCandidates() {
        return electionService.getAllCandidates();
    }

    public static String  getAllVoters() {
        return voterService.getAllVoters();
    }

    public static String  getAllOffers() {
        return electionService.getAllOffers();
    }

//    public static String summarize() {
//        return electionService.getCandidates();
//    }
}
