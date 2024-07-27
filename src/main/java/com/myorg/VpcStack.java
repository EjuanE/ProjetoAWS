package com.myorg;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.SubnetConfiguration;
import software.amazon.awscdk.services.ec2.SubnetType;
import software.amazon.awscdk.services.ec2.*;
import software.constructs.Construct;

import java.util.Arrays;

public class VpcStack extends Stack {
    private final Vpc vpc;

    public VpcStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public VpcStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        this.vpc = Vpc.Builder.create(this, "MyVpc")
                .maxAzs(3) // Máximo de zonas de disponibilidade
                .subnetConfiguration(Arrays.asList(
                        SubnetConfiguration.builder()
                                .subnetType(SubnetType.PUBLIC)
                                .name("PublicSubnet")
                                .cidrMask(24)
                                .build(),
                        SubnetConfiguration.builder()
                                .subnetType(SubnetType.PRIVATE_WITH_NAT)
                                .name("PrivateSubnet")
                                .cidrMask(24)
                                .build()
                ))
                .build();
    }

    public Vpc getVpc() {
        return vpc;
    }
}
