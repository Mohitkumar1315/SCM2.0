package com.scm.SCM20.Helper;

public class ResourcesNotFoundException extends RuntimeException
{
  
    public ResourcesNotFoundException(String msg)
    {
        super(msg);
    }
    public ResourcesNotFoundException()
    {
      super("Customized exceptin");     
    }
}
