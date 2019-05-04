package shotchart.dao;

// @deemus
/**
 * Rajapinta, jonka avulla huolehditaan laukaisukarttojen
 * pysyväistallennuksesta.
 */
import java.util.ArrayList;
import shotchart.domain.ShotChart;

public interface ShotChartDao {

    ShotChart create(ShotChart shotChart) throws Exception;

    ShotChart update(ShotChart shotChart) throws Exception;

    void delete(ShotChart shotChart) throws Exception;

    ShotChart getChart(int id) throws Exception;

    ArrayList<ShotChart> getAll();

}
