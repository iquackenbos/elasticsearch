/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.ccr.rest;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.action.RestToXContentListener;
import org.elasticsearch.xpack.core.ccr.action.GetAutoFollowPatternAction.Request;

import java.io.IOException;

import static org.elasticsearch.xpack.core.ccr.action.GetAutoFollowPatternAction.INSTANCE;

public class RestGetAutoFollowPatternAction extends BaseRestHandler {

    public RestGetAutoFollowPatternAction(Settings settings, RestController controller) {
        super(settings);
        controller.registerHandler(RestRequest.Method.GET, "/_ccr/auto_follow/{leader_cluster_alias}", this);
        controller.registerHandler(RestRequest.Method.GET, "/_ccr/auto_follow", this);
    }

    @Override
    public String getName() {
        return "ccr_get_auto_follow_pattern_action";
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest restRequest, NodeClient client) throws IOException {
        Request request = new Request();
        request.setLeaderClusterAlias(restRequest.param("leader_cluster_alias"));
        return channel -> client.execute(INSTANCE, request, new RestToXContentListener<>(channel));
    }

}
