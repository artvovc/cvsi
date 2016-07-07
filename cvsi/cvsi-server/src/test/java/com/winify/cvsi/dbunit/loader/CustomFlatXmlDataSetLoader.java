package com.winify.cvsi.dbunit.loader;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomFlatXmlDataSetLoader --- utility class, custom data set loader used by DBUnit.
 * 
 */
public class CustomFlatXmlDataSetLoader extends AbstractDataSetLoader {

    @Override
    protected IDataSet createDataSet(Resource resource) throws Exception {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);

        try{
            InputStream inputStream = resource.getInputStream();
            ReplacementDataSet loaded = new ReplacementDataSet(builder.build(inputStream));
            loaded.addReplacementObject("[NULL]", null);
            return loaded;
        }catch(IOException exc){exc.printStackTrace();}
        return null;
    }

    @Override
    public IDataSet loadDataSet(Class<?> testClass, String location) throws Exception {
        final List<IDataSet> iDataSetList = new ArrayList<IDataSet>();
        ResourceLoader resourceLoader = getResourceLoader(testClass);
        String[] resourceLocations = getResourceLocations(testClass, location);
        for (String resourceLocation : resourceLocations) {
            Resource resource = resourceLoader.getResource(resourceLocation);
            if (resource.exists()) {
                iDataSetList.add(createDataSet(resource));
            }
        }
        final IDataSet[] iDataSets = new IDataSet[iDataSetList.size()];
        return new CompositeDataSet(iDataSetList.toArray(iDataSets));
    }

    @Override
    protected String[] getResourceLocations(Class<?> testClass, String location) {
        return location.split(",");
    }
}