package TBAScout;

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
        XYDataset dataset = getDataset(jsonHandler.handleMatchesPojo(new TBAGetRequest("/team/frc141/matches/2018/simple").getJson()), 141);
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);

        add(chartPanel);
    }

    public void redrawGraph(SimpleMatches[] matches, int teamNum) {
        XYDataset dataset = getDataset(matches, teamNum);
        JFreeChart chart = createChart(dataset);

        chartPanel.setChart(chart);
    }

    private XYDataset getDataset(SimpleMatches[] matches, int teamNum) {
        XYSeries series = new XYSeries("2018");
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
        String selectedTeam = "frc" + String.valueOf(teamNum);
        Boolean foundTeam = false;
        int curIteration = 0;

        for (SimpleMatches match : matches) {
            String[] curTeams = match.getAlliances().getBlue().getTeam_keys();
            for (String curTeam : curTeams) {
                if (curTeam.equals(selectedTeam)) {
                    foundTeam = true;
                }
            }
            if (foundTeam) {
                dataPoints.add(new Integer[] {curIteration, Integer.parseInt(match.getAlliances().getBlue().getScore())});
            } else if (!foundTeam) {
                dataPoints.add(new Integer[] {curIteration, Integer.parseInt(match.getAlliances().getRed().getScore())});
            }
            curIteration += 1;
        }

        return dataPoints;
    }

}