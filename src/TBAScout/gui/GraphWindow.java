package TBAScout.gui;

import TBAScout.restrequests.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;

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

import scoutPojo.SimpleMatches;

@SuppressWarnings("serial")
public class GraphWindow extends JPanel {
    ChartPanel chartPanel;
    FinalJsonHandler jsonHandler = new FinalJsonHandler();

    public GraphWindow() {
        try {
            XYDataset dataset = getDataset(jsonHandler.handleMatchesPojo(new TBAGetRequest("/team/frc141/matches/2018/simple").getJson()), 141);
            JFreeChart chart = createChart(dataset);
            chartPanel = new ChartPanel(chart);

            add(chartPanel);
        } catch (ConnectionException e) {
            XYDataset dataset = createNullSet();
            JFreeChart chart = createChart(dataset);
            chartPanel = new ChartPanel(chart);
            
            add(chartPanel);
        }
        
    } 

    public void redrawGraph(SimpleMatches[] matches, int teamNum) {
        XYDataset dataset = getDataset(matches, teamNum);
        JFreeChart chart = createChart(dataset);

        try {
            chartPanel.setChart(chart);
        } catch(NullPointerException e) {
            fixNoInit(chart);
        }
    }

    private XYDataset getDataset(SimpleMatches[] matches, int teamNum) {
        XYSeries series = new XYSeries("team " + teamNum);
        ArrayList<Integer[]> dataPoints = getDPFromMatches(matches, teamNum);

        for (Integer[] score : dataPoints) {
            series.add(score[0], score[1]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "score on a match-by-match basis", 
                "match", 
                "Alliance Score", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );

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

        chart.setTitle(new TextTitle("Team scores",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;

    }

    private ArrayList<Integer[]> getDPFromMatches(SimpleMatches[] matches, int teamNum) {
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
             * data added to curTeamData.scores seems to occasionally be incorrect, resulting
             * in a false display of 0 for a lowest score.
             */
            if (foundTeam) {
                dataPoints.add(new Integer[] {curIteration, Integer.parseInt(match.getAlliances().getBlue().getScore())});
                curTeamData.scores.add(Integer.parseInt(match.getAlliances().getBlue().getScore()));
            } else if (!foundTeam) {
                dataPoints.add(new Integer[] {curIteration, Integer.parseInt(match.getAlliances().getRed().getScore())});
                curTeamData.scores.add(Integer.parseInt(match.getAlliances().getRed().getScore()));
            }

            // if (curTeamData.scores.get(curTeamData.scores.size()-1) == 0) {
            //     System.out.println("blue teams:");
            //     for (String str : match.getAlliances().getBlue().getTeam_keys()) {
            //         System.out.println("    " + str);
            //     }

            //     System.out.println("red teams:");
            //     for (String str : match.getAlliances().getRed().getTeam_keys()) {
            //         System.out.println("    " + str);
            //     }
            //     System.out.println("score (R/B): " + match.getAlliances().getRed().getScore() + " / " + match.getAlliances().getBlue().getScore());
            //     System.out.println("selected team: " + selectedTeam);
            // }
            curIteration += 1;
        }

        curTeamData.calcScoreInfo();

        return dataPoints;
    }

    //
    // methods to fix issues caused by launching without a key set in launch options
    //

    private void fixNoInit(JFreeChart chart) {
        chartPanel = new ChartPanel(chart);
        add(chartPanel);
        updateUI();
    }

    private XYDataset createNullSet() {
        XYSeries series = new XYSeries("null");

        for (int i = 0; i < 10; i++) {
            series.add(i, i);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

}