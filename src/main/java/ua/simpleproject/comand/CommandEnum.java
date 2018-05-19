package ua.simpleproject.comand;

public enum CommandEnum {

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogOutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    OPENCHECK {
        {
            this.command = new OpenCheckCommand();
        }
    },
    OPENCURRENTCHECK {
        {
            this.command = new OpenCurrentCheckCommand();
        }
    },
    ADDPRODUCT {
        {
            this.command = new AddProductCommand();
        }
    },
    OUTX {
        {
            this.command = new OutXCommand();
        }
    },
    OUTZ {
        {
            this.command = new OutYCommand();
        }
    },
    CREATEPRODUCT {
        {
            this.command = new CreateProductCommand();
        }
    },
    ADDPRODUCTSTOCK {
        {
            this.command = new AddProductStockCommand();
        }
    },
    CLOSECHECK {
        {
            this.command = new CloseCheckCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
