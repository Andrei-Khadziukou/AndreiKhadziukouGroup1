import com.company.api.Command;

/**
 * Created by sanko on 10/4/14.
 */
public class CommandKill implements Command {
    @Override
    public String getCommandName() {
        return "Kill somebody";
    }

    @Override
    public void execute() {
        System.out.println("You've been killed!!!");
    }
}
