package br.ufba.pos.charts;

import br.ufba.pos.counter.CounterHolder;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Font;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;

import java.util.Map;

public class Charts {

    public static final String LABEL_GENERIC_ALGORITHM = "Generic Algorithm";
    public static final String LABEL_INPUT_SIZE = "Input size";
    public static final String LABEL_NUMBER_INSTRUCTION = "Number of Instruction";
    public static final String LABEL_DIVIDE_AND_CONQUER_ALGORITHM = "Divide and Conquer Algorithm";

    public static void plotChart(Map<String, CounterHolder> map) {
        int i = 0;
        Trace[] traces = new Trace[map.size()];
        for(Map.Entry<String, CounterHolder> entry : map.entrySet()) {
            String algorithmLabel = entry.getKey();
            Table table = createTable(entry.getValue().getCounterMapInstruction(), algorithmLabel, LABEL_INPUT_SIZE, LABEL_NUMBER_INSTRUCTION);
            System.out.println("\n"+table.printAll());
            Trace trace = createScatterTrace(table, algorithmLabel);
            traces[i] = trace;
            i++;
        }

        Layout layout = createLayout();

        Plot.show(new Figure(layout, traces));
    }

    private static Table createTable(Map<Integer, Integer> map, String tableName, String columnKeyName, String columnValueName) {
        return Table.create(tableName,
                IntColumn.create(columnKeyName, map.keySet().toArray(new Integer[map.size()])),
                IntColumn.create(columnValueName, map.values().toArray(new Integer[map.size()])));
    }

    private static Trace createScatterTrace(Table table, String traceName) {
        return ScatterTrace.builder(table.column(0), table.column(1))
                .mode(ScatterTrace.Mode.LINE_TEXT_AND_MARKERS)
                .name(traceName)
                .build();
    }

    private static Layout createLayout() {
        Font f = Font.builder()
                .family(Font.Family.ARIAL)
                .size(24)
                .color("green")
                .build();

        return Layout.builder("Algorithms", "Input size", "Number of Instruction")
                .titleFont(f)
//                .height(500).width(650)
//                .plotBgColor("blue")
//                .paperBgColor("red")
                .build();
    }
}
