package com.egen.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound()
    {
        super();
    }

    public ResourceNotFound(String msg, Throwable cause)
    {
        super(msg,cause);
    }

    public ResourceNotFound(String msg)
    {
        super(msg);
    }

    public ResourceNotFound(Throwable cause)
    {
        super(cause);
    }

}
