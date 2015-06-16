/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.managedbean;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.PieChartModel;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatBorcService;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "chart")
@RequestScoped
public class ChartBean {
    
    private PieChartModel pieChartModel;
    private DefaultPieDataset dataset;
    @EJB
    private TahsilatBorcService tahsilatBorcService;
    private List<Object[]> chartListe;

    public ChartBean() {
        
         pieChartModel = new PieChartModel();
        pieChartModel.setLegendPosition("ne");
        pieChartModel.setShowDataLabels(true);
        pieChartModel.setTitle("KURUM BORÇ GRAFİĞİ");
        pieChartModel.setDataFormat("value");
        dataset = new DefaultPieDataset();
        chartListe = new ArrayList<Object[]>();
    }
    
    @PostConstruct
    public void doldurChart()
    {
        chartListe = tahsilatBorcService.chartVerisiGetir();
        
        for (Object[] chartElement : chartListe) {
            pieChartModel.set(String.valueOf(chartElement[0]), Double.valueOf(chartElement[1].toString()));
        }
        
        /*pieChartModel.set("ASKİ", 1400);
        pieChartModel.set("PTT", 200);
        pieChartModel.set("TELEKOM", 1000);*/
    }

    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }

    public void setPieChartModel(PieChartModel pieChartModel) {
        this.pieChartModel = pieChartModel;
    }
    
    
    public StreamedContent getJfreeChart() {
        StreamedContent content = null;
        try {
            chartListe = tahsilatBorcService.chartVerisiGetir();

            for (Object[] chartElement : chartListe) {
                dataset.setValue(String.valueOf(chartElement[0]), Double.valueOf(chartElement[1].toString()));
            }

            boolean legend = true, tooltip = true, urls = false;

            JFreeChart chart = ChartFactory.createPieChart("JFreeChart",
                    dataset, legend, tooltip, urls);
            File chartFile = new File("jfreechart");
            int width = 375, height = 300;
            ChartUtilities.saveChartAsPNG(chartFile, chart, width,
                    height);
            content = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return content;
    }
    
    
}
