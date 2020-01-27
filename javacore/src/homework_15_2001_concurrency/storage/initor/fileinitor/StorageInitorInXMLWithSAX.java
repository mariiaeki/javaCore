package homework_15_2001_concurrency.storage.initor.fileinitor;

import homework_15_2001_concurrency.cargo.domain.BasicCargo;
import homework_15_2001_concurrency.carrier.domain.Carrier;
import homework_15_2001_concurrency.common.company_exceptions.checked.InitStorageException;
import homework_15_2001_concurrency.common.util.FileUtils;
import homework_15_2001_concurrency.storage.initor.fileinitor.saxhandlers.EntitySAXHandler;
import homework_15_2001_concurrency.storage.initor.fileinitor.saxhandlers.saxhandlersstack.EntitySAXHandlerWithStack;
import homework_15_2001_concurrency.transportation.domain.Transportation;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;
import java.util.Map;

public class StorageInitorInXMLWithSAX extends BaseStorageInitorInFile
{
    private static final String FILE = "/xmldata.xml";

    @Override
    public void storageInitor() throws InitStorageException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            //EntitySAXHandler saxHandler = new EntitySAXHandler();
            EntitySAXHandlerWithStack saxHandler= new EntitySAXHandlerWithStack();

            File file = FileUtils.createFileFromResource("xmldata", "xmldata-file", FILE);
            saxParser.parse(file, saxHandler);

            Map<String, BasicCargo> cargoMap = saxHandler.getParsedCargos();
            Map<String, Carrier> carrierMap = saxHandler.getParsedCarriers();
            List<ParsedTransportation> transportations = saxHandler.getParsedTransportations();
            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
