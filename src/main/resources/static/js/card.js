const stripe = Stripe('pk_test_51PUo1Y06Jq3kkCxJeGWywSW6FsOy4Umc2YTxRYLj5DvHTDxGlUdh7gklR51UDjxOlSw6eT8l2Z2qECWITbNWQZUU00KqzduPgw');
const elements = stripe.elements();

// Create an instance of the card Element.
const card = elements.create('card', { hidePostalCode: true });

// Add an instance of the card Element into the `card-element` <div>.
card.mount('#cardElement');

const cardHolderName = document.getElementById('cardHolderName');
const cardButton = document.getElementById('cardButton');

cardButton.addEventListener('click', function (event) {
    event.preventDefault();

    stripe.createPaymentMethod({
        type: 'card',
        card: card,
        billing_details: { name: cardHolderName.value, },
    }).then(function (result) {
        if (result.error) {
            const errorElement = document.getElementById('カード情報に不備があります。');
            errorElement.textContent = error.message;
        } else {
            const form = document.getElementById('paymentForm');

            const hiddenInput = document.createElement('input');
            hiddenInput.setAttribute('type', 'hidden');
            hiddenInput.setAttribute('name', 'paymentMethodId');
            hiddenInput.setAttribute('value', result.paymentMethod.id);
            form.appendChild(hiddenInput);

            form.submit();
        }
    });
});

