package au.com.qut.cpm.capstone.system.io.email;

import lombok.Data;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Data
public final class EmailWrapper {

    private final String template;
    private final String subject;
    private final boolean toIndividuals;
    private final List<String> sendTo = new ArrayList<>();
    private final Context context = new Context();
}
