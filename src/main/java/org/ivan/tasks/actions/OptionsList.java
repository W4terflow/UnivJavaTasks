package org.ivan.tasks.actions;

import org.ivan.tasks.actions.optiontypes.OperationOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionsList {
    private final List<OperationOption> options = new ArrayList<>();

    public OptionsList addOption(OperationOption option) {
        options.add(option);
        return this;
    }

    public Optional<OperationOption> getExpressionByCode(String code) {
        return options.stream().filter(option -> option.getOptionCode().equals(code)).findFirst();
//        if(result.isEmpty()) {
//            throw new IllegalArgumentException("Несуществующее значение code для ExpressionOption: " + code);
//        }
//        return result.get();
    }

    public Optional<OperationOption> getExpressionByNumber(int number) { // starting at 1
        if(number > 0 && options.size() > number - 1) return Optional.of(options.get(number - 1));
        return Optional.empty();
    }

    public String formatOptionsList() {
        StringBuilder sb = new StringBuilder("Возможные операции:\n");
        int i = 0;
        for (OperationOption option : options) {
            i++;
            sb.append(i).append(". ").append(option.getOptionDescription()).append('\n');
        }

        return sb.toString();
    }
}
