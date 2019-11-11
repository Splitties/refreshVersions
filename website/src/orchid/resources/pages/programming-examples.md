---
title: Examples - Programming
---

### Syntax Highlighting with Pygments 

```pebble
{% verbatim %}
{% highlight language="java" %}
    public abstract class OrchidGenerator extends Prioritized implements OptionsHolder {
        
        protected final String key;
    
        protected final OrchidContext context;
    
        @Inject
        public OrchidGenerator(OrchidContext context, String key, int priority) {
            super(priority);
            this.key = key;
            this.context = context;
        }
    
        /**
         * A callback to build the index of content this OrchidGenerator intends to create.
         *
         * @return a list of pages that will be built by this generator
         */
        public abstract List<? extends OrchidPage> startIndexing();
    
        /**
         * A callback to begin generating content. The index is fully built and should not be changed at this time. The
         * list of pages returned by `startIndexing` is passed back in as an argument to the method.
         *
         * @param pages the pages to render
         */
        public abstract void startGeneration(Stream<? extends OrchidPage> pages);
    }
{% endhighlight %}
{% endverbatim %}
```

{% highlight language="java" %}
    public abstract class OrchidGenerator extends Prioritized implements OptionsHolder {
        
        protected final String key;
    
        protected final OrchidContext context;
    
        @Inject
        public OrchidGenerator(OrchidContext context, String key, int priority) {
            super(priority);
            this.key = key;
            this.context = context;
        }
    
        /**
         * A callback to build the index of content this OrchidGenerator intends to create.
         *
         * @return a list of pages that will be built by this generator
         */
        public abstract List<? extends OrchidPage> startIndexing();
    
        /**
         * A callback to begin generating content. The index is fully built and should not be changed at this time. The
         * list of pages returned by `startIndexing` is passed back in as an argument to the method.
         *
         * @param pages the pages to render
         */
        public abstract void startGeneration(Stream<? extends OrchidPage> pages);
    }
{% endhighlight %}


***

### Embed Github Gist

```pebble
{% verbatim %}
{% gist user="cjbrooks12" id="83a11f066388c9fe905ee1bab47ecca8" %}
{% endverbatim %}
```

{% gist user="cjbrooks12" id="83a11f066388c9fe905ee1bab47ecca8" %}

***
