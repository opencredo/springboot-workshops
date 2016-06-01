To record a call record, post this to /api/v1/call-records/records:

```json
  {
    "id": "a75de37c-27f9-11e6-b67b-9e71128cae77",
    "ownerId": "f36966ce-ce4c-4b05-84d9-23d338ad577d",
    "inbound": {
      "number": "0898505050",
      "start": 1000,
      "end": 2000
    },
    "outbound": {
      "number": "01604123456",
      "start": 1000,
      "end": 200
    },
    "outcome": "OK"
  }
```
