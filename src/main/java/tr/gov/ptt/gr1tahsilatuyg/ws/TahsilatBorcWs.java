/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.ws;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatBorc;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatBorcService;

/**
 *
 * @author Administrator
 */
@WebService(serviceName = "TahsilatBorcWs")
public class TahsilatBorcWs {
    @EJB
    private TahsilatBorcService ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "kurumAdlariGetir")
    public List<String> kurumAdlariGetir(@WebParam(name = "query") String query) {
        return ejbRef.kurumAdlariGetir(query);
    }

    @WebMethod(operationName = "findAllBorcViaKurumIdAndAboneNo")
    public List<TahsilatBorc> findAllBorcViaKurumIdAndAboneNo(@WebParam(name = "p_kurumad") String p_kurumad, @WebParam(name = "p_aboneNo") String p_aboneNo) {
        return ejbRef.findAllBorcViaKurumIdAndAboneNo(p_kurumad, p_aboneNo);
    }

    @WebMethod(operationName = "seciliFaturalariOde")
    public boolean seciliFaturalariOde(@WebParam(name = "p_seciliBorclar") List<TahsilatBorc> p_seciliBorclar, @WebParam(name = "p_kisi") TahsilatKisi p_kisi) {
        return ejbRef.seciliFaturalariOde(p_seciliBorclar, p_kisi);
    }

    @WebMethod(operationName = "chartVerisiGetir")
    public List<Object[]> chartVerisiGetir() {
        return ejbRef.chartVerisiGetir();
    }
    
}
