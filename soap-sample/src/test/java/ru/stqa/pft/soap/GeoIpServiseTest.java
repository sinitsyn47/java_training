package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;
import com.lavasoft.GetIpLocation20;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiseTest {

  @Test
  public void testMyIp(){
    new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("5.18.221.82");
    //assertTrue(geoIP.contains("RU"));

  }
}
