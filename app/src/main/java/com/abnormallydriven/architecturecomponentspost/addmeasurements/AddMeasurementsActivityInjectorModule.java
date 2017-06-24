package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import dagger.Module;

//makes this a subcomponent of whatever component the module is installed in.
@Module(subcomponents = AddMeasurementActivitySubcomponent.class)
public class AddMeasurementsActivityInjectorModule { }
