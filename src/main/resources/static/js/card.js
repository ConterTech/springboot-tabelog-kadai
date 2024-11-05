const stripe = Stripe('pk_test_51PUo1Y06Jq3kkCxJeGWywSW6FsOy4Umc2YTxRYLj5DvHTDxGlUdh7gklR51UDjxOlSw6eT8l2Z2qECWITbNWQZUU00KqzduPgw');
const elements = stripe.elements();

// Create an instance of the card Element.
const card = elements.create('card', { hidePostalCode: true });

// Add an instance of the card Element into the `card-element` <div>.
card.mount('#cardElement');

const cardButton = document.getElementById('cardButton');

cardButton.addEventListener('click', function (event) {
    event.preventDefault();

    // 初期化
    document.getElementById('nameError').innerHTML = '';
    document.getElementById('cardError').innerHTML = ''; 
    let getHolderName = true;

    // 入力データ取得
    const cardHolderName = document.getElementById('cardHolderName');

    if (!cardHolderName.value) {
        document.getElementById('nameError').innerHTML = '<div>カード名義人を入力してください。</div>'; 
        getHolderName = false;
    }

    stripe.createPaymentMethod({
        type: 'card',
        card: card,
        billing_details: { name: cardHolderName.value, },
    }).then(function (result) {
        if (result.error) {
            document.getElementById('cardError').innerHTML = '<div>カード情報に不備が存在します。</div>'; 
        } else if (getHolderName) {
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

