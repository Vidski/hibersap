package org.hibersap.examples.jee;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;
import org.hibersap.configuration.AnnotationConfiguration;
import org.hibersap.examples.flightdetail.FlightDetailBapi;
import org.hibersap.examples.flightlist.FlightListBapi;
import org.hibersap.session.Session;
import org.hibersap.session.SessionFactory;
import org.jboss.annotation.ejb.RemoteBinding;

@Stateless
@RemoteBinding(jndiBinding = SapFlightService.JNDI_NAME)
@Remote(SapFlightService.class)
public class SapFlightServiceBean
    implements SapFlightService
{
    private static final Logger LOG = Logger.getLogger( SapFlightServiceBean.class );

    private SessionFactory sessionFactory;

    @PostConstruct
    public void init()
    {
        LOG.info( "Initializing flight service" );
        final AnnotationConfiguration configuration = new AnnotationConfiguration();
        sessionFactory = configuration.buildSessionFactory();
        LOG.info( "DONE Initializing flight service" );
    }

    /**
     * Example using Container Managed Transaction (CMT): The container cares about transaction
     * handling (see @TransactionAttribute annotation).
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public FlightDetailBapi showFlightDetail( final Date date )
    {
        LOG.info( "showFlightDetail" + date );
        final Session session = sessionFactory.openSession();

        try
        {
            final FlightDetailBapi flightDetail = new FlightDetailBapi( "AZ", "0788", date );
            session.execute( flightDetail );
            LOG.info( "DONE showFlightDetail" + flightDetail );
            return flightDetail;
        }
        finally
        {
            session.close();
        }
    }

    /**
     * Example using Bean Managed Transaction (BMT): No Transaction from the Container is propagated
     * (see @TransactionAttribute annotation), so we do programmatic transaction handling.
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public FlightListBapi showFlightList()
    {
        LOG.info( "showFlightList" );
        final Session session = sessionFactory.openSession();

        try
        {
            session.beginTransaction();
            final FlightListBapi flightList = new FlightListBapi( "DE", "Frankfurt", "DE", "Berlin", null, false, 10 );
            session.execute( flightList );
            session.getTransaction().commit();
            LOG.info( "DONE showFlightList" + flightList );
            return flightList;
        }
        finally
        {
            session.close();
        }
    }
}
