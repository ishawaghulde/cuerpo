package com.example.cuerpo.mainscreens.exercisetrack;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuerpo.R;
import com.example.cuerpo.initialscreens.DbHandler;
import com.example.cuerpo.initialscreens.MainActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM;

public class SummaryFragment extends Fragment {
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList<BarEntry> barEntries;
    private DbHandler dbHandler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary,container,false);
        barChart = view.findViewById(R.id.bargraph);
        dbHandler = new DbHandler(getActivity());
        barEntries = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todayDate);
        for(int i =0; i<7; i++){
            calendar.add(Calendar.DAY_OF_YEAR, -i);
            Date newDate = calendar.getTime();
            String pastDate = formatter.format(newDate);
            boolean exists = dbHandler.GetUserByDate(pastDate);
            if(exists){
                int entry = dbHandler.GetStepsByDate(pastDate);
                barEntries.add(new BarEntry((float)(7-i-1), (float)entry));
            }
            else{
                barEntries.add(new BarEntry(0f, 0f));
            }
        }

//
//        barEntries.add(new BarEntry(1f, 6000f));
//        barEntries.add(new BarEntry(2f, 5500f));
//        barEntries.add(new BarEntry(3f, 8900f));
//        barEntries.add(new BarEntry(4f, 4700f));
//        barEntries.add(new BarEntry(5f, 8200f));
//        barEntries.add(new BarEntry(6f, 7300f));
        barDataSet = new BarDataSet(barEntries, "STEPS");
        barDataSet.setColor(getResources().getColor(R.color.turquoise));






        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMaximum(15000f);
        LimitLine ll = new LimitLine(10000f, "Goal Reached");
        ll.setLineColor(getResources().getColor(R.color.turquoise));
        ll.setLineWidth(2f);
        ll.setTextColor(Color.BLACK);
        ll.setTextSize(12f);
        leftAxis.addLimitLine(ll);
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);

        class DateAxisValueFormatter implements IAxisValueFormatter {
            private String[] mValues = {"2019.07.26", "2019.07.27", "2019.07.28", "2019.07.29", "2019.07.30", "2019.07.31", "2019.08.01" };

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");


            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return sdf.format(new Date((long) value));
            }
        }
        XAxis xAxis = barChart.getXAxis();
        xAxis.setLabelRotationAngle(45f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);

//        class MyXAxisFormatter : ValueFormatter() {
//            private val days = arrayOf("Mo", "Tu", "Wed", "Th", "Fr", "Sa", "Su")
//            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
//                return days.getOrNull(value.toInt()) ?: value.toString()
//            }
//        }

        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barData.setBarWidth(0.7f); // set custom bar width
        barChart.setData(barData);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate();

        return view;
    }
}
