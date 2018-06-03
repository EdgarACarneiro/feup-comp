package worker.workers;

import report.WorkerReport;
import spoon.reflect.declaration.*;
import spoon.reflect.visitor.filter.AbstractFilter;
import spoon.reflect.visitor.filter.TypeFilter;
import util.Operations;
import worker.Worker;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Count the number of direct methods defined in a given Class (does not include constructors nor methods belonging to sub-classes)
 */
public class W_classMethods extends Worker {
    public W_classMethods(CtElement rootNode, String patternName) {
        super(rootNode, patternName);
    }

    @Override
    protected AbstractFilter setFilter() {
        return new TypeFilter<>(CtClass.class);
    }

    @Override
    public WorkerReport call() throws Exception {
        return new WorkerReport(
                rootNode.filterChildren(new AbstractFilter<CtMethod>(CtMethod.class) {
                    @Override
                    public boolean matches(CtMethod method) {
                        return method.getParent() == rootNode; // guarantee this is a direct child
                    }
                }).list().size()
        );
    }
}
