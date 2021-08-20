# act-issue-with-kind-registry

This is a sample application use to share my kind registry issu with the great tool [act](https://github.com/nektos/act).

## two issues with [container-tools/kind-action@v1.5.0](https://github.com/container-tools/kind-action)

### Could not open file /etc/docker/daemon.json

```log
[Sample integration test/kind_integration_test]   🐳  docker exec cmd=[node /var/run/act/actions/container-tools-kind-action@v1.5.0/main.js] user=
| Creating registry "kind-registry" on port 5000 from image "registry:2"...
| 127.0.0.1 kind-registry
| jq: error: Could not open file /etc/docker/daemon.json: No such file or directory
| docker: unrecognized service
```

It has been by pass with 

```yaml
- name: daemon.json hack
          # link to https://github.com/container-tools/kind-action/blob/main/registry.sh#L133
          if: ${{ env.ACT }}
          run: |
            mkdir -p /etc/docker
            echo -n "{\"exec-opts\": [\"native.cgroupdriver=systemd\"],\"log-driver\": \"json-file\",\"log-opts\": {\"max-size\": \"100m\"},\"storage-driver\": \"overlay2\"}" > /etc/docker/daemon.json

```

### docker: unrecognized service

This command [sudo service docker restart](https://github.com/container-tools/kind-action/blob/main/registry.sh#L136) is working on github but not locally with act.

```bash
[Sample integration test/kind_integration_test]   🐳  docker exec cmd=[mkdir -p /var/run/act/actions/container-tools-kind-action@v1.5.0/] user=
[Sample integration test/kind_integration_test]   🐳  docker exec cmd=[node /var/run/act/actions/container-tools-kind-action@v1.5.0/main.js] user=
| Creating registry "kind-registry" on port 5000 from image "registry:2"...
| 127.0.0.1 kind-registry
| docker: unrecognized service
```