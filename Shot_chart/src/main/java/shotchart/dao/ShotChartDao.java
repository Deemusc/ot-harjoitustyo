package shotchart.dao;

// @deemus

import java.util.ArrayList;
import java.util.List;
import shotchart.domain.ShotChart;


public interface ShotChartDao {
    ShotChart create(ShotChart shotChart) throws Exception;

    ShotChart update(ShotChart shotChart) throws Exception;
    
    ArrayList<ShotChart> getAll();
        
}
