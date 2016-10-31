package com.example.user.akjoll;
import com.backendless.BackendlessUser;

public class AkJollUser extends BackendlessUser
{
  public String getEmail()
  {
    return super.getEmail();
  }

  public void setEmail( String email )
  {
    super.setEmail( email );
  }

  public String getPassword()
  {
    return super.getPassword();
  }

  public String getName()
  {
    return (String) super.getProperty( "name" );
  }

  public void setName( String name )
  {
    super.setProperty( "name", name );
  }

  public Integer getStatus()
  {
    return (Integer) super.getProperty("status");
  }

  public void setStatus( Integer status )
  {
    super.setProperty( "status", status );
  }
}