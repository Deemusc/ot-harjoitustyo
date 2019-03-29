package shotchart.dao;

// @deemus

import java.util.List;
import shotchart.domain.ShotChart;


public interface ShotChartDao {
    ShotChart create(ShotChart shotChart) throws Exception;
    
    List<ShotChart> getAll();
        
}
