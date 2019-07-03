build:
	docker build -t empirechaotix/inventory-service:0.1.0 .
	docker push empirechaotix/inventory-service:0.1.0
create:
	oc new-project inventory-service
deploy:
	oc project inventory-service
	oc apply -f openshift/Deployment.yml
# 	oc apply -f openshift/Gateway.yml
	oc apply -f openshift/Service.yml
undeploy:
	oc project inventory-service
	oc delete -f openshift/Deployment.yml
# 	oc delete -f openshift/Gateway.yml
	oc delete -f openshift/Service.yml