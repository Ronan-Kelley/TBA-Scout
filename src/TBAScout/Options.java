package TBAScout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import scoutPojo.*;

public class Options {
    /**
     * this class exists entirely to keep track of command line options
     */
    private static Boolean cli = false;
    private static String TBAKey = null; // put your key here, or pass it to the program as an argument
    private static String path = "none";
    private static Boolean makeChart = false;
    private static String fileName = null;

    //
    // getters and setters
    //

    public static void setCli(Boolean cli) {
        Options.cli = cli;
    }

    public static Boolean getCli() {
        return cli;
    }

    public static void setTBAKey(String TBAKey) {
        Options.TBAKey = TBAKey;
    }

    public static String getTBAKey() {
        return TBAKey;
    }

    public static void setPath(String path) {
        Options.path = path;
    }

    public static String getPath() {
        return path;
    }

    private static void setMakeChart(Boolean makeChart) {
        Options.makeChart = makeChart;
    }

    private static Boolean getMakeChart() {
        return Options.makeChart;
    }

    private static void setFileName(String fileName) {
        Options.fileName = fileName;
    }

    private static String getFileName() {
        return Options.fileName;
    }

    public static void handleArgs(String[] args) {
        if (args != null) {
            for (String arg : args) {
                switch (arg) {
                case "-cli": // check for "-cli" argument, disable graphics if present
                    Options.setCli(true);
                    // System.out.println("found argument: " + arg);
                    break;
                case "-key": // make sure "-key" is specified before looping through all this
                    Boolean foundDashKey = false;
                    Boolean foundKey = false;
                    String keyArg = "";
                    for (int i = 0; i < args.length; i++) { // loop back through the array of arguments
                        if (!foundDashKey) {
                            if (args[i].equals("-key")) { // find the argument dictating a key
                                foundDashKey = true;
                            }
                        } else if (foundDashKey && !foundKey) {
                            if (args[i].contains("-") == false) {
                                foundKey = false; // once the argument dictating a key is found, everything after it
                                                  // that comes before the next key should be the actual key
                                keyArg += args[i];
                            } else if (args[i].contains("-")) {
                                foundKey = true;
                            }
                        }
                    }
                    Options.setTBAKey(keyArg);
                    // System.out.println("found argument pair: " + arg + ": " + keyArg);
                    break;

                case "-path":
                    /**
                     * logic here is identical to the above case, if there is confusion check those
                     * comments
                     */
                    Boolean foundDashPath = false;
                    Boolean foundPath = false;
                    String pathArg = "";

                    for (int i = 0; i < args.length; i++) {
                        if (!foundDashPath) {
                            if (args[i].equals("-path")) {
                                foundDashPath = true;
                            }
                        } else if (foundDashPath && !foundPath) {
                            if (args[i].contains("-") == false) {
                                foundPath = false;
                                pathArg += args[i];
                            } else if (args[i].contains("-")) {
                                foundPath = true;
                            }
                        }
                    }
                    // System.out.println("found argument pair: " + arg + ": " + pathArg);
                    Options.setPath(pathArg.replace(" ", "")); // make sure the path doesn't include spaces, since none
                                                               // of the actual paths on TBA do
                    break;

                case "-chart":

                    Options.setMakeChart(true);

                    break;

                case "-o":

                    Options.setFileName("");

                    Boolean foundDashO = false;
                    Boolean foundO = false;
                    String OArg = "";

                    for (int i = 0; i < args.length; i++) {
                        if (!foundDashO) {
                            if (args[i].equals("-o")) {
                                foundDashO = true;
                            }
                        } else if (foundDashO && !foundO) {
                            if (args[i].contains("-") == false) {
                                foundO = false;
                                OArg += args[i];
                            } else if (args[i].contains("-")) {
                                foundO = true;
                            }
                        }
                    }

                    Options.setFileName(OArg);

                    break;
                }
            }
        } else if (args == null || args.length == 0) {
            System.out.println("no arguments found!");
        }

        if (Options.getCli() && !Options.getPath().equals("none") && Options.getTBAKey() != null) {
            System.out.println(new TBAGetRequest(Options.getPath()).getJson());
        }

        if (Options.getTBAKey() != null && Options.getPath().contains("matches") && Options.getMakeChart()) {
            String imgName = Options.getFileName();

            if (Options.getFileName() == null) {
                imgName = "image";
            }

            int teamNum;

            try {
                teamNum = Integer.parseInt(Options.getPath().replaceAll("(?mi)\\/|team|frc|matches.*", "")); // get the
                                                                                                             // team
                                                                                                             // number
                                                                                                             // from the
                                                                                                             // request
                                                                                                             // path
            } catch (NumberFormatException e) {
                System.out.println("Options.handleArgs failed to handle chart creation functionality!");
                teamNum = 0;
            }

            // is this a horribly ugly and potentially confusing way of doing this?
            // Probably.

            try {
            writeChartToDisk(
                    makeChart(new FinalJsonHandler().handleMatchesPojo(new TBAGetRequest(Options.getPath()).getJson()),
                            teamNum),
                    imgName);
            } catch (ConnectionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //
    // methods here are more or less copy pasted from GraphWindow
    //

    private static ArrayList<Integer[]> getDPFromMatches(SimpleMatches[] matches, int teamNum) {
        ArrayList<Integer[]> dataPoints = new ArrayList<Integer[]>();
        curTeamData.scores = new ArrayList<Integer>();
        String selectedTeam = "frc" + String.valueOf(teamNum);
        Boolean foundTeam = false;
        int curIteration = 1;

        for (SimpleMatches match : matches) {
            String[] curTeams = match.getAlliances().getBlue().getTeam_keys();
            foundTeam = false;
            for (String curTeam : curTeams) {
                if (curTeam.equals(selectedTeam)) {
                    foundTeam = true;
                }
            }

            /**
             * data added to curTeamData.scores seems to occasionally be incorrect,
             * resulting in a false display of 0 for a lowest score.
             */
            if (foundTeam) {
                dataPoints.add(
                        new Integer[] { curIteration, Integer.parseInt(match.getAlliances().getBlue().getScore()) });
                curTeamData.scores.add(Integer.parseInt(match.getAlliances().getBlue().getScore()));
            } else if (!foundTeam) {
                dataPoints.add(
                        new Integer[] { curIteration, Integer.parseInt(match.getAlliances().getRed().getScore()) });
                curTeamData.scores.add(Integer.parseInt(match.getAlliances().getRed().getScore()));
            }

            // if (curTeamData.scores.get(curTeamData.scores.size()-1) == 0) {
            // System.out.println("blue teams:");
            // for (String str : match.getAlliances().getBlue().getTeam_keys()) {
            // System.out.println(" " + str);
            // }

            // System.out.println("red teams:");
            // for (String str : match.getAlliances().getRed().getTeam_keys()) {
            // System.out.println(" " + str);
            // }
            // System.out.println("score (R/B): " + match.getAlliances().getRed().getScore()
            // + " / " + match.getAlliances().getBlue().getScore());
            // System.out.println("selected team: " + selectedTeam);
            // }
            curIteration += 1;
        }

        curTeamData.calcScoreInfo();

        return dataPoints;
    }

    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart("score on a match-by-match basis", "match", "Alliance Score",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Team scores", new Font("Serif", java.awt.Font.BOLD, 18)));

        return chart;

    }

    private static XYDataset getDataset(SimpleMatches[] matches, int teamNum) {
        XYSeries series = new XYSeries("team " + teamNum);
        ArrayList<Integer[]> dataPoints = getDPFromMatches(matches, teamNum);

        for (Integer[] score : dataPoints) {
            series.add(score[0], score[1]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private static JFreeChart makeChart(SimpleMatches[] matches, int teamNum) {
        XYDataset dataset = getDataset(matches, teamNum);
        return createChart(dataset);
    }

    //
    // finally, some new methods not from GraphWindow
    //

    private static void writeChartToDisk(JFreeChart chart, String imgName) {
        BufferedImage objBufferedImage = chart.createBufferedImage(800, 800);
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            ImageIO.write(objBufferedImage, "png", bas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] byteArray = bas.toByteArray();

        InputStream in = new ByteArrayInputStream(byteArray);
        BufferedImage image = null;

        try {
            image = ImageIO.read(in);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("couldn't output image!");
        }

        File outputfile = new File(imgName + ".png");

        try {
            ImageIO.write(image, "png", outputfile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("couldn't output image!");
        }

    }
}