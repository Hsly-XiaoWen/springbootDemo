package com.juemuren.config.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * refer to ConfigurationPropertiesBindingPostProcessor
 * Created by 肖文 on 2018/11/30.
 */
@Component
public class DataSourcePropertiesProcessor implements ApplicationContextAware, BeanFactoryAware,
        EnvironmentAware, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(DataSourcePropertiesProcessor.class);

    private PropertySources propertySources;

    private ConversionService conversionService;

    private DefaultConversionService defaultConversionService;

    private List<Converter<?, ?>> converters = Collections.emptyList();

    private List<GenericConverter> genericConverters = Collections.emptyList();

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    private Environment environment;

    public void postProcessBeforeInitialization(Object bean, String beanName, String prefix) {
        Object target = bean;
        PropertiesConfigurationFactory<Object> factory = new PropertiesConfigurationFactory<Object>(
                target);
        factory.setPropertySources(this.propertySources);
        factory.setValidator(null);
        // If no explicit conversion service is provided we add one so that (at least)
        // comma-separated arrays of convertibles can be bound automatically
        factory.setConversionService(this.conversionService == null
                ? getDefaultConversionService() : this.conversionService);
        factory.setTargetName(prefix);
        try {
            factory.bindPropertiesToTarget();
        }
        catch (Exception ex) {
            String targetClass = ClassUtils.getShortName(target.getClass());
            throw new BeanCreationException(beanName, "Could not bind properties to "
                    + targetClass + " (" + prefix + ")", ex);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.propertySources == null) {
            this.propertySources = deducePropertySources();
        }
        if (this.conversionService == null) {
            this.conversionService = getOptionalBean(
                    ConfigurableApplicationContext.CONVERSION_SERVICE_BEAN_NAME,
                    ConversionService.class);
        }
    }


    private ConversionService getDefaultConversionService() {
        if (this.defaultConversionService == null) {
            DefaultConversionService conversionService = new DefaultConversionService();
            this.applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
            for (Converter<?, ?> converter : this.converters) {
                conversionService.addConverter(converter);
            }
            for (GenericConverter genericConverter : this.genericConverters) {
                conversionService.addConverter(genericConverter);
            }
            this.defaultConversionService = conversionService;
        }
        return this.defaultConversionService;
    }

    private PropertySources deducePropertySources() {
        PropertySourcesPlaceholderConfigurer configurer = getSinglePropertySourcesPlaceholderConfigurer();
        if (configurer != null) {
            // Flatten the sources into a single list so they can be iterated
            return new FlatPropertySources(configurer.getAppliedPropertySources());
        }
        if (this.environment instanceof ConfigurableEnvironment) {
            MutablePropertySources propertySources = ((ConfigurableEnvironment) this.environment)
                    .getPropertySources();
            return new FlatPropertySources(propertySources);
        }
        // empty, so not very useful, but fulfils the contract
        logger.warn("Unable to obtain PropertySources from "
                + "PropertySourcesPlaceholderConfigurer or Environment");
        return new MutablePropertySources();
    }

    private PropertySourcesPlaceholderConfigurer getSinglePropertySourcesPlaceholderConfigurer() {
        // Take care not to cause early instantiation of all FactoryBeans
        if (this.beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) this.beanFactory;
            Map<String, PropertySourcesPlaceholderConfigurer> beans = listableBeanFactory
                    .getBeansOfType(PropertySourcesPlaceholderConfigurer.class, false,
                            false);
            if (beans.size() == 1) {
                return beans.values().iterator().next();
            }
            if (beans.size() > 1 && logger.isWarnEnabled()) {
                logger.warn("Multiple PropertySourcesPlaceholderConfigurer "
                        + "beans registered " + beans.keySet()
                        + ", falling back to Environment");
            }
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    private <T> T getOptionalBean(String name, Class<T> type) {
        try {
            return this.beanFactory.getBean(name, type);
        }
        catch (NoSuchBeanDefinitionException ex) {
            return null;
        }
    }

    /**
     * A list of custom converters (in addition to the defaults) to use when converting
     * properties for binding.
     * @param converters the converters to set
     */
    @Autowired(required = false)
    @ConfigurationPropertiesBinding
    public void setConverters(List<Converter<?, ?>> converters) {
        this.converters = converters;
    }

    /**
     * A list of custom converters (in addition to the defaults) to use when converting
     * properties for binding.
     * @param converters the converters to set
     */
    @Autowired(required = false)
    @ConfigurationPropertiesBinding
    public void setGenericConverters(List<GenericConverter> converters) {
        this.genericConverters = converters;
    }


    /**
     * Convenience class to flatten out a tree of property sources without losing the
     * reference to the backing data (which can therefore be updated in the background).
     */
    private static class FlatPropertySources implements PropertySources {

        private PropertySources propertySources;

        FlatPropertySources(PropertySources propertySources) {
            this.propertySources = propertySources;
        }

        @Override
        public Iterator<PropertySource<?>> iterator() {
            MutablePropertySources result = getFlattened();
            return result.iterator();
        }

        @Override
        public boolean contains(String name) {
            return get(name) != null;
        }

        @Override
        public PropertySource<?> get(String name) {
            return getFlattened().get(name);
        }

        private MutablePropertySources getFlattened() {
            MutablePropertySources result = new MutablePropertySources();
            for (PropertySource<?> propertySource : this.propertySources) {
                flattenPropertySources(propertySource, result);
            }
            return result;
        }

        private void flattenPropertySources(PropertySource<?> propertySource,
                                            MutablePropertySources result) {
            Object source = propertySource.getSource();
            if (source instanceof ConfigurableEnvironment) {
                ConfigurableEnvironment environment = (ConfigurableEnvironment) source;
                for (PropertySource<?> childSource : environment.getPropertySources()) {
                    flattenPropertySources(childSource, result);
                }
            }
            else {
                result.addLast(propertySource);
            }
        }

    }
}
