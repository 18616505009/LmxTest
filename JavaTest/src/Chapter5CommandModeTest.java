/**
 * @author lmx
 * @date 2020-05-12 13:40
 * 模拟《SpringCloud微服务实战第五章》
 */
public class Chapter5CommandModeTest {

    //接收者
    public static class Receiver {
        public void action() {
            System.out.println("接收者action调用,执行真正的业务逻辑");
        }
    }

    //Command:抽象命令
    //定义了一个命令对象应有的一系列命令操作,如execute(),undo(),redo()
    //当命令的某个操作被调用时就会触发接收者去做对应的具体业务逻辑
    static interface Command {
        void execute();
    }

    //ConcreteCommand:具体命令实现
    //例如此处绑定了与接收者之间的关系,execute()命令的实现委托给了Receiver的action()函数
    public static class ConcreteCommand implements Command {

        Receiver receiver;

        public ConcreteCommand(Receiver receiver) {
            this.receiver = receiver;
        }

        @Override
        public void execute() {
            receiver.action();
        }
    }

    //客户端调用者
    public static class Invoker {
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        public void action() {
            this.command.execute();
        }
    }

    //main方法模拟客户端调用者invoker发送命令的执行流程
    public static void main(String[] args) {

        //receiver有action方法，里面包含了真正的处理业务逻辑
        Receiver receiver = new Receiver();
        //Command是接口,其实现类ConcreteCommand指定了一个receiver作为命令的实现者
        Command command = new ConcreteCommand(receiver);
        //invoker作为客户端调用者,包含设置命令、执行命令(再调用接收者执行)两个功能
        //invoker调用者,持有一个命令对象command,可以通过命令对象完成具体的业务逻辑
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.action();
    }

}
