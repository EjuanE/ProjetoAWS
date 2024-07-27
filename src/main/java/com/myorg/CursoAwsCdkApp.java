package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

import java.util.Arrays;

public class CursoAwsCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        VpcStack vpc = new VpcStack(app, "Vpc");

        ClusterStack cluster = new ClusterStack(app, "Cluster", vpc.getVpc());
        cluster.addDependency(vpc);

        RdsStack rds = new RdsStack(app, "Rds", vpc.getVpc());
        rds.addDependency(vpc);

        Service01Stack service = new Service01Stack(app, "Service01", cluster.getCluster());
        service.addDependency(cluster);
        service.addDependency(rds);



        app.synth();
    }
}

